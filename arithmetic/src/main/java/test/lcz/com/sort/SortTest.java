package test.lcz.com.sort;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 廖灿中
 * @Email: liaocanzhong@seengene.com
 * @Date: 2019-07-01
 */
public class SortTest {
    private static final String BASE_PATH = "/sdcard/SeengeneBenzFactory/";
    private static final String SHELF_ONE_MAP_PATH = BASE_PATH + "benz_shelf_one.yml";
    private static final String SHELF_TWO_MAP_PATH = BASE_PATH + "benz_shelf_two.yml";
    private static final String SHELF_THREE_MAP_PATH = BASE_PATH + "benz_shelf_three.yml";
    private static final String SHELF_FOUR_MAP_PATH = BASE_PATH + "benz_shelf_four.yml";
    private static final String SHELF_FIVE_MAP_PATH = BASE_PATH + "benz_shelf_five.yml";
    private static final String SHELF_SIX_MAP_PATH = BASE_PATH + "benz_shelf_six.yml";
    private static final String SHELF_SEVEN_MAP_PATH = BASE_PATH + "benz_shelf_seven.yml";
    private static List<SelectTask> mSelectNetTasks = new ArrayList<>();
    private static List<SelectTask> mSelectTasks = new ArrayList<>();
    private static Map<Integer, String> mIdMap = new HashMap<>();
    private static Map<String, Integer> mMapIndex = new HashMap<>();
    private static Map<String, Address> mAddressMap = new HashMap<>();
    private static Map<String, Integer> mSpecialProductIndex = new HashMap<>();

    public static void main(String[] args) {
        initMap();
        String result = readToString("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/sort/one.txt");
        mSelectNetTasks = JSON.parseObject(result, new TypeReference<ArrayList<SelectTask>>() {
        });
        Map<String, Map<String, SelectTask.PickOrderItems>> resultMap = new HashMap<>();
        for (int i = 0; i < mSelectNetTasks.size(); i++) {
            SelectTask selectTask = mSelectNetTasks.get(i);
            List<SelectTask.PickOrderItems> pickOrderItemsList = selectTask.getPickOrderItems();
            for (SelectTask.PickOrderItems pickOrderItem : pickOrderItemsList) {
                pickOrderItem.setTagAdress("0001A" + (i + 1));
                System.out.println("pickOrderItem: address  " + pickOrderItem.toString());
                if (resultMap.containsKey(pickOrderItem.getPartNo() + pickOrderItem.getColorNo())) {
                    Map aa = resultMap.get(pickOrderItem.getPartNo() + pickOrderItem.getColorNo());
                    if (aa == null) {
                        Map<String, SelectTask.PickOrderItems> item = new HashMap<>();
                        item.put(pickOrderItem.getTagAdress(), pickOrderItem);
                        resultMap.put(pickOrderItem.getPartNo() + pickOrderItem.getColorNo(), item);
                    } else {
                        if (!aa.containsKey(pickOrderItem.getTagAdress())) {
                            aa.put(pickOrderItem.getTagAdress(), pickOrderItem);
                            System.out.println("aa size  " + aa.size());
                            resultMap.put(pickOrderItem.getPartNo() + pickOrderItem.getColorNo(), aa);
                        }
                    }
                } else {
                    Map<String, SelectTask.PickOrderItems> item = new HashMap<>();
                    item.put(pickOrderItem.getTagAdress(), pickOrderItem);
                    resultMap.put(pickOrderItem.getPartNo() + pickOrderItem.getColorNo(), item);
                }
            }
        }
        SelectTask one = mSelectNetTasks.get(0);
        for (Map<String, SelectTask.PickOrderItems> stringPickOrderItemsMap : resultMap.values()) {
            SelectTask selectTask = new SelectTask();
            selectTask.setId(one.getId());
            selectTask.setCode(one.getCode());
            selectTask.setProductId(one.getProductId());
            selectTask.setSheftNo(one.getSheftNo());
            selectTask.setStatus(one.getStatus());
            selectTask.setOperatingState(one.getOperatingState());
            selectTask.setCreatedAt(one.getCreatedAt());
            selectTask.setCreatedBy(one.getCreatedBy());
            List<SelectTask.PickOrderItems> items = new ArrayList<>();
            if (stringPickOrderItemsMap != null) {
                for (SelectTask.PickOrderItems pickOrderItems : stringPickOrderItemsMap.values()) {
                    items.add(pickOrderItems);
                }
                selectTask.setPickOrderItems(items);
                System.out.println("selectTask   " + selectTask);
                mSelectTasks.add(selectTask);
            }
        }
        for (SelectTask selectTask : mSelectTasks) {
            System.out.println("=== before ====== mSelectTasks  selectTask  " + selectTask.getPickOrderItems().get(0).getPartNo() + selectTask.getPickOrderItems().get(0).getColorNo());
        }
        mSelectTasks.sort((o1, o2) -> {
            SelectTask.PickOrderItems one1 = o1.getPickOrderItems().get(0);
            SelectTask.PickOrderItems two = o2.getPickOrderItems().get(0);
            System.out.println("====== one1   " + one1.getPartNo() + one1.getColorNo() + " two " + two.getPartNo() + two.getColorNo());
            if (one1 != null && two != null) {
                if (mSpecialProductIndex.size() > 0) {
                    if (mSpecialProductIndex.containsKey(one1.getPartNo() + one1.getColorNo()) &&
                            mSpecialProductIndex.containsKey(two.getPartNo() + two.getColorNo())) {
                        int ss = mSpecialProductIndex.get(one1.getPartNo() + one1.getColorNo()) -
                                mSpecialProductIndex.get(two.getPartNo() + two.getColorNo());
                        System.out.println("====== ----- containsKey one1 containsKey  two ss    " + ss);
                        return ss;
                    } else if (mSpecialProductIndex.containsKey(one1.getPartNo() + one1.getColorNo()) &&
                            !mSpecialProductIndex.containsKey(two.getPartNo() + two.getColorNo())) {
                        System.out.println("------ ====== containsKey one1  not containsKey two  -1    ");
                        return -1;
                    } else if (!mSpecialProductIndex.containsKey(one1.getPartNo() + one1.getColorNo()) &&
                            mSpecialProductIndex.containsKey(two.getPartNo() + two.getColorNo())) {
                        System.out.println("------ ======  not containsKey one1   containsKey two  1    ");
                        return 1;
                    }
                }
                if (mAddressMap.size() > 0 && mAddressMap.containsKey(two.getPartNo() + two.getColorNo()) &&
                        mAddressMap.containsKey(one1.getPartNo() + one1.getColorNo())) {
                    String oneMap = mAddressMap.get(one1.getPartNo() + one1.getColorNo()).getMapPath();
                    String twoMap = mAddressMap.get(two.getPartNo() + two.getColorNo()).getMapPath();
                    if (mMapIndex.size() > 0 && mMapIndex.containsKey(oneMap) && mMapIndex.containsKey(twoMap)) {
                        int cc = mMapIndex.get(oneMap) - mMapIndex.get(twoMap);
                        System.out.println("------ ====== ---   mMapIndex  containsKey one1   containsKey two  1    ");
                        return cc;
                    }
                }
            }
            System.out.println("====== sort one1 is null two is null     ");
            return 0;
        });

        for (SelectTask selectTask : mSelectTasks) {
            System.out.println("====== mSelectTasks  selectTask  " + selectTask.getPickOrderItems().get(0).getPartNo() + selectTask.getPickOrderItems().get(0).getColorNo());
        }
    }

    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    private static void initMap() {
        mIdMap.put(7, SHELF_ONE_MAP_PATH);
        mIdMap.put(8, SHELF_ONE_MAP_PATH);
        mIdMap.put(14, SHELF_ONE_MAP_PATH);
        mIdMap.put(16, SHELF_ONE_MAP_PATH);
        mIdMap.put(92, SHELF_ONE_MAP_PATH);
        mIdMap.put(101, SHELF_ONE_MAP_PATH);
        mIdMap.put(446, SHELF_ONE_MAP_PATH);
        mIdMap.put(447, SHELF_ONE_MAP_PATH);
        mIdMap.put(448, SHELF_ONE_MAP_PATH);
        mIdMap.put(449, SHELF_ONE_MAP_PATH);

        mIdMap.put(11, SHELF_TWO_MAP_PATH);
        mIdMap.put(12, SHELF_TWO_MAP_PATH);
        mIdMap.put(13, SHELF_TWO_MAP_PATH);
        mIdMap.put(17, SHELF_TWO_MAP_PATH);
        mIdMap.put(18, SHELF_TWO_MAP_PATH);
        mIdMap.put(193, SHELF_TWO_MAP_PATH);
        mIdMap.put(218, SHELF_TWO_MAP_PATH);
        mIdMap.put(232, SHELF_TWO_MAP_PATH);
        mIdMap.put(233, SHELF_TWO_MAP_PATH);
        mIdMap.put(235, SHELF_TWO_MAP_PATH);
        mIdMap.put(263, SHELF_TWO_MAP_PATH);
        mIdMap.put(441, SHELF_TWO_MAP_PATH);
        mIdMap.put(442, SHELF_TWO_MAP_PATH);
        mIdMap.put(443, SHELF_TWO_MAP_PATH);
        mIdMap.put(444, SHELF_TWO_MAP_PATH);
        mIdMap.put(445, SHELF_TWO_MAP_PATH);

        mIdMap.put(27, SHELF_THREE_MAP_PATH);
        mIdMap.put(29, SHELF_THREE_MAP_PATH);
        mIdMap.put(42, SHELF_THREE_MAP_PATH);
        mIdMap.put(43, SHELF_THREE_MAP_PATH);
        mIdMap.put(126, SHELF_THREE_MAP_PATH);
        mIdMap.put(127, SHELF_THREE_MAP_PATH);
        mIdMap.put(128, SHELF_THREE_MAP_PATH);
        mIdMap.put(129, SHELF_THREE_MAP_PATH);
        mIdMap.put(130, SHELF_THREE_MAP_PATH);

        mIdMap.put(38, SHELF_FOUR_MAP_PATH);
        mIdMap.put(39, SHELF_FOUR_MAP_PATH);
        mIdMap.put(45, SHELF_FOUR_MAP_PATH);
        mIdMap.put(46, SHELF_FOUR_MAP_PATH);
        mIdMap.put(47, SHELF_FOUR_MAP_PATH);
        mIdMap.put(48, SHELF_FOUR_MAP_PATH);
        mIdMap.put(67, SHELF_FOUR_MAP_PATH);
        mIdMap.put(69, SHELF_FOUR_MAP_PATH);
        mIdMap.put(121, SHELF_FOUR_MAP_PATH);
        mIdMap.put(122, SHELF_FOUR_MAP_PATH);
        mIdMap.put(123, SHELF_FOUR_MAP_PATH);
        mIdMap.put(124, SHELF_FOUR_MAP_PATH);

        mIdMap.put(20, SHELF_FIVE_MAP_PATH);
        mIdMap.put(40, SHELF_FIVE_MAP_PATH);
        mIdMap.put(41, SHELF_FIVE_MAP_PATH);
        mIdMap.put(49, SHELF_FIVE_MAP_PATH);
        mIdMap.put(65, SHELF_FIVE_MAP_PATH);
        mIdMap.put(68, SHELF_FIVE_MAP_PATH);
        mIdMap.put(96, SHELF_FIVE_MAP_PATH);
        mIdMap.put(110, SHELF_FIVE_MAP_PATH);
        mIdMap.put(118, SHELF_FIVE_MAP_PATH);
        mIdMap.put(119, SHELF_FIVE_MAP_PATH);
        mIdMap.put(120, SHELF_FIVE_MAP_PATH);

        mIdMap.put(0, SHELF_SIX_MAP_PATH);
        mIdMap.put(1, SHELF_SIX_MAP_PATH);
        mIdMap.put(2, SHELF_SIX_MAP_PATH);
        mIdMap.put(59, SHELF_SIX_MAP_PATH);
        mIdMap.put(111, SHELF_SIX_MAP_PATH);
        mIdMap.put(112, SHELF_SIX_MAP_PATH);
        mIdMap.put(113, SHELF_SIX_MAP_PATH);
        mIdMap.put(114, SHELF_SIX_MAP_PATH);
        mIdMap.put(115, SHELF_SIX_MAP_PATH);
        mIdMap.put(116, SHELF_SIX_MAP_PATH);
        mIdMap.put(117, SHELF_SIX_MAP_PATH);

        mIdMap.put(506, SHELF_SEVEN_MAP_PATH);
        mIdMap.put(507, SHELF_SEVEN_MAP_PATH);
        mIdMap.put(518, SHELF_SEVEN_MAP_PATH);
        mIdMap.put(519, SHELF_SEVEN_MAP_PATH);
        mIdMap.put(520, SHELF_SEVEN_MAP_PATH);
        mIdMap.put(521, SHELF_SEVEN_MAP_PATH);
        mIdMap.put(522, SHELF_SEVEN_MAP_PATH);
        mIdMap.put(580, SHELF_SEVEN_MAP_PATH);

        mMapIndex.put(SHELF_ONE_MAP_PATH, 1);
        mMapIndex.put(SHELF_TWO_MAP_PATH, 2);
        mMapIndex.put(SHELF_THREE_MAP_PATH, 3);
        mMapIndex.put(SHELF_FOUR_MAP_PATH, 4);
        mMapIndex.put(SHELF_FIVE_MAP_PATH, 5);
        mMapIndex.put(SHELF_SIX_MAP_PATH, 6);
        mMapIndex.put(SHELF_SEVEN_MAP_PATH, 0);

        mSpecialProductIndex.put("A20581002051C36", 1);
        mSpecialProductIndex.put("A20581001051C36", 2);
        mSpecialProductIndex.put("A20581002059H43", 3);
        mSpecialProductIndex.put("A20581001059H43", 4);
        mSpecialProductIndex.put("A21381055018U26", 5);
        mSpecialProductIndex.put("A21381056018U26", 6);
        mSpecialProductIndex.put("A21381055017M94", 7);
        mSpecialProductIndex.put("A21381056017M94", 8);

        String resultString = readToString("/Users/liaocanzhong/work/JavaArithmetic/arithmetic/src/main/java/test/lcz/com/sort/benz_address_114.json");
        mAddressMap.clear();
        if (resultString != null && resultString.length() > 0) {
            List<Address> addresses = JSON.parseObject(resultString, new TypeReference<ArrayList<Address>>() {
            });
            if (addresses != null && addresses.size() > 0) {
                for (Address address : addresses) {
                    System.out.println("initMap: address size " + address.toString());
                    mAddressMap.put(address.getId(), address);
                }
            }
        }

    }

}

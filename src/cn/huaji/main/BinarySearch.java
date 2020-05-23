package cn.huaji.main;

/**
 * @ClassName BinarySearch
 * @Description 二分查找
 * @author 滑技工厂 https://blog.csdn.net/qq_41718454
 * @date 2020/3/11
 * @version 1.0
 */
public class BinarySearch {

    /*
     * @Title recursiveBinarySearch
     * @Description 递归的二分查找
     * @author 滑技工厂
     * @Date 2020/3/11
     * @param [arr, key, low, high]
     * @return int
     * @throws
     */
    public static int recursiveBinarySearch(int[] arr, int key, int low, int high) {
        //找到最后，啥也没找到，low都大于high了
        if (low > high)
            return -1;

        int middle = (low + high) / 2;
        //等于key值
        if (key == arr[middle])
            return middle;
        else if (key < arr[middle])
            //key小，说明key在low————middle-1之间
            return recursiveBinarySearch(arr, key, low, middle - 1);
        else
            //key大，说明key在middle+1——————high之间
            return recursiveBinarySearch(arr, key, low + 1, high);
    }

    /*
     * @Title nonRecursiveBinarySearch
     * @Description 非递归的二分查找
     * @author 滑技工厂
     * @Date 2020/3/11
     * @param [arr, key]
     * @return int
     * @throws
     */
    public static int nonRecursiveBinarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        int middle;

        while (low <= high) {
            middle = (low + high) / 2;
            if (arr[middle] == key)
                return middle;
            else if (arr[middle] < key)
                low = middle + 1;
            else if (arr[middle] > key)
                high = middle - 1;
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 5, 6, 8, 9};
        System.out.println(nonRecursiveBinarySearch(arr, 10));

    }

}

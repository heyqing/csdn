# 算法设计与数据结构系列【超详细、超全面、小白可入，期末复习】

> ***持续更新中…***
>
> 24.07.22
>
> 代码采用语言：**Java**

# 1、位运算（Bitwise Operation）

- 常见操作：与（`&`）、或（`I`）、非（`~`）、异或（`^`）
- 移位运算：`>> `和 `<<` 分别为左移和右移
- `>>>`运算符：用0填充高位，`>>`用符号位填充高位，**没有`<<<`运算符**

**真值表**

|  a   |  b   |  ~a  |  ~b  | a&b  | a\|b | a^b  |
| :--: | :--: | :--: | :--: | :--: | :--: | :--: |
|  1   |  1   |  0   |  0   |  1   |  1   |  0   |
|  0   |  1   |  1   |  0   |  0   |  1   |  1   |
|  1   |  0   |  0   |  1   |  0   |  1   |  1   |
|  0   |  0   |  1   |  1   |  0   |  0   |  0   |

## 1.1、常见的位运算技巧

- 判断奇偶数
- 获取二进制位是1还是0
- 交换两个整数变量的值
- 不用判断语句，求出整数的绝对值
- …

## 1.2、判断奇偶数

**使用与运算**

- 奇数二进制的特点：`...0100...1 `其末尾数字必为`1`
- 偶数二进制的特点：`...0100...0`其末尾数字必为`0`

> 故只需将目标数和1做与运算即可得到结果

代码描述

```java
	/**
     * 判断奇偶数-与
     * <p>
     * return true is odd
     *
     * @param number
     * @return
     */
    public static Boolean OddOrEven(int number) {
        return (number & 1) == 1;
    }
```

## 1.3、找到落单的数

**使用异或运算**

_题目描述_：在一个**奇数个元素**数组中**有且仅有一个数**只出现过一次，其余数皆为成双出现，找出这个落单的数字

- A ^ A = 0
- A ^ 0 = A

> 故只需将目标数组中元素依次进行异或运算
>
> 第一个与0进行异或

代码描述

```java
	/**
     * 找到落单的数-异或
     * <p>
     * return target number
     *
     * @param targetArray
     * @return
     */
    public static Integer findSingularNumber(int[] targetArray) {
        int targetNumber = 0;
        for (int num : targetArray) {
            targetNumber ^= num;
        }
        return targetNumber;
    }
```

## 1.4、找出成双的数

**使用异或运算**

_题目描述_：在一个**奇数个元素**数组中**有且仅有一个数**只出现过两次，其余数皆出现1次，找出这个成双的数字

> 只需将目标数组中元素依次进行异或运算
>
> 如果结果为0即为目标数字

代码描述

```java
	/**
     * 找出成双的数-异或
     * <p>
     * return target number
     *
     * @param targetArray
     * @return
     */
    public static Integer findPairNumberByXOR(int[] targetArray) {
        int targetNumber = 0;
        for (int num : targetArray) {
            if ((targetNumber ^ num) == 0) {
                break;
            }
            targetNumber = num;
        }
        return targetNumber;
    }
```

**使用辅助空间**

> 遍历目标数组，计数
>
> 1、使用数组辅助：数组的下标为目标数组的值，数组对应下标的值为目标数组值出现的次数
>
> 2、使用map辅助：key为目标数组的值，value为目标数组值出现的次数

代码描述

```java
	/**
     * 找出成双的数-辅助空间
     * <p>
     * return target number
     *
     * @param targetArray
     * @return
     */
    public static Integer findPairNumberByHelperSpace(int[] targetArray) {
        int targetNumber = 0;
        //辅助空间，1、辅助空间的下标为目标数组的值，辅助空间对应下标的值为目标数组值出现的次数，缺点为目标数组中的值范围不确定
        //这里假设我就假设目标数组中元素最大不超过999，当然你可以使用List
        int[] helperArray = new int[999];
        for (int num : targetArray) {
            helperArray[num]++;
            //如果辅助数组值为2说明此下标出现了两次
            if (helperArray[num] == 2) {
                targetNumber = num;
                break;
            }
        }
        //辅助空间，2、使用map，key为目标数组的值，如果同一个key出现储存两次两次的情况，则此数值为成双数值
        targetNumber = 0;
        Map<Integer, Integer> helperMap = new HashMap<>();
        for (int num : targetArray) {
            boolean isExist = helperMap.containsKey(num);
            if (isExist) {
                //如果存在，则说明此数在此数组出现第二次
                targetNumber = num;
                break;
            } else {
                helperMap.put(num, 1);
            }
        }
        return targetNumber;
    }
```

## 1.5、二进制数中1的个数

**使用移位运算-移动目标数字相与**

- 1 & 1 = 1
- 1 & 0 = 0

> 将目标数字右移和1相与
>
> 如果相与结果为1则计数加1
>
> exp: 
>
> 10110
>
> 1：0 & 1 = 0 count + 0 ，10110 >> 1 ==> 1011
>
> 2：1 & 1 = 1 count + 1，1011>> 1 ==>101
>
> …
>
> count = 3

代码实现

```java
	/**
     * 二进制数中1的个数-移动数字
     * <p>
     * return counts
     *
     * @param number
     * @return
     */
    public static Integer findBinary1CountsByMoveNumber(int number) {
        int counts = 0;
        //因为int为4byte即2^32，故须循环32次
        for (int i = 0; i < 32; i++) {
            if (((number >> i) & 1) == 1) {
                counts++;
            }
        }
        return counts;
    }
```

**使用移位运算-移动1相与**

> 将1左移和目标数字相与
>
> exp: 
>
> 10110
>
> 1：0 & 1 = 0 count + 0 ，1 << 1 ==> 10
>
> 2：1 & 1 = 1 count + 1，10 << 1 ==>100
>
> …
>
> count = 3

代码实现

```java
	/**
     * 二进制数中1的个数-移动1
     * <p>
     * return counts
     *
     * @param number
     * @return
     */
    public static Integer findBinary1CountsByMove1(int number) {
        int counts = 0;
        //因为int为4byte即2^32，故须循环32次
        for (int i = 0; i < 32; i++) {
            if ((number & (1 << i)) == (1 << i)) {
                counts++;
            }
        }
        return counts;
    }
```

**使用移位运算-减1相与**

> 将目标数字与目标数字-1相与，直至目标数字为0
>
> 过程中相与的次数为1的个数
>
> exp:
>
> 10110
>
> 10110 - 1 ==> 10101
>
> 10110 & 10101 = 10100 每次相与会将最后一个1“抵消”成0

代码实现

```java
	/**
     * 二进制数中1的个数-减1
     * <p>
     * return counts
     *
     * @param number
     * @return
     */
    public static Integer findBinary1CountsBySubtraction1(int number) {
        int counts = 0;
        while (number != 0) {
            counts++;
            number &= (number - 1);
        }
        return counts;
    }
```

## 1.6、2的整数幂

**使用二进制数中1的个数方法**

_题目描述_：判断一个数是不是2的整数幂

- 2的整数幂数的特点：二进制数中有且仅有一个1

代码实现

```java
	/**
     * 2的整数幂
     *
     * @param number
     * @return
     */
    public static Boolean isPowerOf2(int number) {
        //选用 二进制数中1的个数 方法之一
        //二进制数中1的个数为1的为2的整数幂
        return (number & (number - 1)) == 0;
    }
```

## 1.7、奇偶位互换

**使用与、异或、移位运算**

_题目描述_：将一个整数的二进制的奇偶位互换

代码实现

```java
	/**
     * 奇偶位互换
     *
     * @param number
     * @return
     */
    public static Integer OddAndEvenSwap(int number) {
        int even = 0xaaaaaaaa;
        int odd = 0x55555555;
        return ((number&even)>>1)^((number&odd)<<1);
    }
```

## 1.8、出现K次与出现1次

**本题主要运用k个k进制数不进位相加结果为0特性**

> exp：
>
> k：2
>
> 1 1 2 2 3 4 4
>
> 将其都转化为k进制即2进制
>
> 1 1 10 10 11 100 100
>
> 1 + 1 不进位结果为 0
>
> 10 + 10 不进位结果为 0
>
> 100 + 100 不进位结果为 0
>
> 0 + 0+ 0 + 11 结果为 11
>
> 再转为10进制即3

本题主要讲解思路，无代码实现

代码实现可采用Map等其他方法
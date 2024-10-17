#include<stdio.h>

void swap();

int main() {
	swap();
	return 0;
}

void swap() {
	int a = 42;		//定义变量 a
	int* pa = &a;	//定义指针 pa 指向变量 a 的存储地址
 	int* pb = pa;	//定义指针 pb 等于 pa 同变量 a 的存储地址
	printf("before the swap a = %d ,b = %d \n", a, *pb);	//打印运行前 a ，b 的值
	a = a ^ (*pb);			//由于a和b是相同的，这里实际上执行的是 a = a ^ a
	(*pb) = a ^ (*pb);		//现在a是0即*pb也为0，所以 b = 0 ^ b = 0 ^ 0 = 0
	a = a ^ (*pb);			//现在a是0,*pb也为0，所以 a = 0 ^ 0 = 0
	printf("after the swap a = %d ,b = %d", a, *pb);		//打印运行后 a ，b 的值
}
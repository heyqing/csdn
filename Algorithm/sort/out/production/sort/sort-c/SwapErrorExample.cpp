#include<stdio.h>

void swap();

int main() {
	swap();
	return 0;
}

void swap() {
	int a = 42;		//������� a
	int* pa = &a;	//����ָ�� pa ָ����� a �Ĵ洢��ַ
 	int* pb = pa;	//����ָ�� pb ���� pa ͬ���� a �Ĵ洢��ַ
	printf("before the swap a = %d ,b = %d \n", a, *pb);	//��ӡ����ǰ a ��b ��ֵ
	a = a ^ (*pb);			//����a��b����ͬ�ģ�����ʵ����ִ�е��� a = a ^ a
	(*pb) = a ^ (*pb);		//����a��0��*pbҲΪ0������ b = 0 ^ b = 0 ^ 0 = 0
	a = a ^ (*pb);			//����a��0,*pbҲΪ0������ a = 0 ^ 0 = 0
	printf("after the swap a = %d ,b = %d", a, *pb);		//��ӡ���к� a ��b ��ֵ
}
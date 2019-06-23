// sp1.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//reverse a string

#include "pch.h"
#include <iostream>
#include <string>
using namespace std;

void reverseIterate(const string & copied, string & s)
{
	for (int i = 0; i < copied.length(); i++)
		s += copied[copied.length() - i - 1];
}

void reverse(const string & copied, string & s)
{
	char *pc = (char *)&copied[0];
	if (*pc)
	{
		char c = *pc++;
		reverse(string(pc), s);
		s += c;
	}
}

int main()
{
	string s1, s2;
	cout << "input a string: ";
	cin >> s1;
//	reverseIterate(s1, s2);
	reverse(s1, s2);
	cout << s2 << endl;
}

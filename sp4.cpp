// sp4.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include "pch.h"
#include <iostream>
#include <string>
using namespace std;

bool palindrome(const string & s);

int main()
{
	string s;
	cout << "input a string: ";
	cin >> s;
	cout << palindrome(s) << endl;
}

bool palindrome(const string & s)
{
	bool flag = true;
	for (int i = 0; i < s.length() / 2; i++)
		if (s[i] != s[s.length() - 1 - i])
		{
			flag = false;
			break;
		}
	return flag;
}
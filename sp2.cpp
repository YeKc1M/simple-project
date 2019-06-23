// sp2.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include "pch.h"
#include <iostream>
#include <string>
using namespace std;

bool isVowel(const char & c);
void PigLatin(const string & str, string & pl)
{
	string s = str;
	int i = 0;
	for (char c : s)
	{
		if (!isVowel(c))
			break;
		i++;
	}
	char c = s[i];
	for (int j = i; j < s.length() - 1; j++)
		s[j] = s[j + 1];
	s[s.length() - 1] = c;
	s += "ay";
	pl = s;
}

int main()
{
	string s;
	cout << "input string: ";
	cin >> s;
	string pl;
	PigLatin(s, pl);
	cout << pl << endl;
}

bool isVowel(const char & c)
{
	return(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y');
}
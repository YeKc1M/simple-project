// sp3.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include "pch.h"
#include <iostream>
#include <string>
using namespace std;

int main()
{
	string s;
	cout << "input a string: ";
	cin >> s;
	int arr[5] = { 0 };//count the number of each vowel
	for (char c : s)
	{
		if (c == 'a')
			arr[0]++;
		else if (c == 'e'|| c == 'E')
			arr[1]++;
		else if (c == 'i'|| c == 'I')
			arr[2]++;
		else if (c == 'o'|| c == 'O')
			arr[3]++;
		else if (c == 'u'|| c == 'U')
			arr[4]++;
	}
	int counter = 0;
	cout << "a " << arr[0] << endl << "e " << arr[1] << endl << "i " << arr[2] << endl << "o " << arr[3] << endl << "u " << arr[4] << endl;
}


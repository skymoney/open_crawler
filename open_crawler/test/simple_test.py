#-*- coding:utf-8 -*-

import requests
from bs4 import BeautifulSoup

def crawl_single_page():
	url = ''

	response = requests.get(url)

	if response.status_code == 200:
		print response.content

def crawl_page_set():
	start_url = 'http://www.zhihu.com/explore'

	allowd_domain = 'zhihu.com'

	response = requests.get(start_url)

	if response.status_code == 200:
		dom = BeautifulSoup(response.content)

		print dom.title.text.encode('utf-8')



if __name__ == '__main__':
	crawl_page_set()
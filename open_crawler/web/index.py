#-*- coding:utf-8 -*-

from flask import Flask

app = Flask(__name__)

@app.route('/', methods=['GET'])
def index():
	return "Test"



if __name__ == '__main__':
	app.debug = True
	app.run()

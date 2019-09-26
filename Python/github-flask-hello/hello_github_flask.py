# -*- coding: utf-8 -*-  # TODO do that anywhere
from flask import Flask
from flask_github import GitHub

app = Flask(__name__)
app.config['GITHUB_CLIENT_ID'] = 'langenhagen'
app.config['GITHUB_CLIENT_SECRET'] = 'ca3GITHUB7c65'

# For GitHub Enterprise
app.config['GITHUB_BASE_URL'] = 'https://HOSTNAME/api/v3/'
app.config['GITHUB_AUTH_URL'] = 'https://HOSTNAME/login/oauth/'

github = GitHub(app)

# # setup flask
# app = Flask(__name__)
# app.config.from_object(__name__)


@app.route('/login')
def login():
    return github.authorize()
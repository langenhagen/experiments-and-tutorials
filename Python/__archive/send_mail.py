
''' Simple mail sending program
'''
print 'Sending mail to the Devil...'

import datetime

SMTPserver = 'smtp.strato.de'
sender =     'the.devil@langenhagen.cc'
destination = ['the.devil@langenhagen.cc']

USERNAME = "the.devil@langenhagen.cc"
PASSWORD = "the.devil123"

# typical values for text_subtype are plain, html, xml
text_subtype = 'plain'


content="""\
Your build is ready
""" + str(datetime.datetime.now())

subject="Your build is ready :* @ " + str(datetime.datetime.now())

import sys
import os
import re


from smtplib import SMTP_SSL as SMTP       # this invokes the secure SMTP protocol (port 465, uses SSL)
# from smtplib import SMTP                 # use this for standard SMTP protocol   (port 25, no encryption)

# old version
# from email.MIMEText import MIMEText
from email.mime.text import MIMEText

try:
    msg = MIMEText(content, text_subtype)
    msg['Subject']= subject
    msg['From']   = sender # some SMTP servers will do this automatically, not all

    conn = SMTP(SMTPserver)
    conn.set_debuglevel(False)
    conn.login(USERNAME, PASSWORD)
    try:
        conn.sendmail(sender, destination, msg.as_string())
    finally:
        conn.quit()

except Exception, exc:
    sys.exit( "Mail failed; %s" % str(exc) ) # give a error message

print 'Mail to the devil has been sent'
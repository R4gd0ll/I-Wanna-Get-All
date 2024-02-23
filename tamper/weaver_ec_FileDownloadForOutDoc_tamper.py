import os,re,random
from lib.core.enums import PRIORITY
from lib.core.common import singleTimeWarnMessage
from lib.core.enums import DBMS
'''
Author    :   R4gd0ll
'''
priority = PRIORITY.HIGHEST

def tamper(payload, **kwargs):
	result = ""
	num = random.randint(1,2**27)
	result = str(num)+payload
	return result

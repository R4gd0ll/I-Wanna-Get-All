import random
from lib.core.enums import PRIORITY

'''
Author    :   R4gd0ll
'''
priority = PRIORITY.HIGHEST

def tamper(payload, **kwargs):
	result = ""
	num = random.randint(1,2**27)
	result = str(num)+payload
	return result

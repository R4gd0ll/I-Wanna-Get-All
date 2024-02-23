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


    payload = "user|0|"+payload

    return payload
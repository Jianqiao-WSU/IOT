# -*- coding: utf-8 -*-
# @Time    : 2019/5/22 22:52
# @Author  : FTP
# @Email   : cugftp@foxmail.com
# @File    : forward_real_video_stream.py
import cv2
import os
import webbrowser

def forward_real_time_video_stream_to_hls():
    '''
    转发视频流，转发的是本地的摄像头的视频流到hls，这个函数只能在本地调用，不能在服务器端调用
    :return:
    '''
    command = 'ffmpeg  -re -i rtmp://58.200.131.2:1935/livetv/hunantv  -vcodec libx264 -acodec aac -ar 44100 -strict -2 -ac 1 -f flv -s 1280x720 rtmp://47.100.9.221:1935/hls/test'
    os.system(command)

if __name__=="__main__":
    forward_real_time_video_stream_to_hls()
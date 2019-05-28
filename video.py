import cv2
import os
import time

# 从视频流获取视频
def getvideo():
    # 乔安摄像头自带视频流地址
  #  url = 'rtsp://192.168.2.230:554/stream1'
    url = 'rtsp://192.168.1.115:554/stream1'
    cap = cv2.VideoCapture(url)
  #  size = (int(cap.get(cv2.CAP_PROP_FRAME_WIDTH)), int(cap.get(cv2.CAP_PROP_FRAME_HEIGHT)))
  #  forucc = cv2.VideoWriter_fourcc(*'XVID')
  #  out = cv2.VideoWriter('.\videos\out.avi', forucc, 20, size)
    file_num=0
    while (cap.isOpened()):
        # Capture frame-by-frame
        ret, frame = cap.read()
        # Display the resulting frame
        if ret == True:
            cv2.imshow('frame', frame)
            # 保存图片路径
            # tmppath = os.path.join("./", "tmp/")
            # 保存每帧(frame)的图片
            # cv2.imwrite(tmppath+str(file_num)+".jpg",frame)
           # file_num=file_num+1
            # 保存帧为视频
        #    out.write(frame)
            if cv2.waitKey(25) & 0xFF == ord('q'):
                break
        else:
            break
    # When everything done, release the capture  
    cap.release()
   #out.release()
    cv2.destroyAllWindows()

if __name__ == '__main__':
	getvideo()
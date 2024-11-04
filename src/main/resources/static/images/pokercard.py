from glob import glob
import os

# 현재 폴더 밑에 PNG-cards-1.3 폴더가 있고 거기에 *.png 파일이 위치
[os.rename(f, "c_{}".format(f)) for f in glob("*.png")]
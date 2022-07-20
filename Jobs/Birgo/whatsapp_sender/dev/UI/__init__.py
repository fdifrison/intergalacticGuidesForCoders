import pathlib
import os
img_file_name = r"\img\birgo_logo.png"
current_dir = pathlib.Path(__file__).parent.resolve()  # current directory
# join with your image's file name
print(current_dir)
img_path = os.path.join(current_dir, img_file_name)

print(img_path)

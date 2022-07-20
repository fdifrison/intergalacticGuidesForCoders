__author__ = "Giovanni Frison"
__credits__ = ["Giovanni Frison"]
__License__ = "GNU AGPLv3"
__maintainer__ = "Giovanni Frison"
__email__ = "ing.giovanni.frison@gmail.com"
__status__ = "Development"
__version__ = "0.0.1"

# Default python packages
from email.mime import image
import os
import pathlib
from re import X
import tkinter as tk
from tkinter import font as tkFont
from fontTools.ttLib import TTFont


# pip installed python packages
from PIL import ImageTk, Image
from numpy import pad


def new_button(text: str, parent: tk.Frame, font: tkFont.Font,
               width: int = 100, row: int = 0, col: int = 0, stick: str = '',
               grid: bool = True, pack: bool = False):
    button = tk.Button(parent, text=text, width=width)
    button['font'] = font
    if grid:
        button.grid(row=row, columnspan=col, sticky=stick, padx=5, pady=5)
    if pack:
        button.pack(fill=tk.BOTH, pady=10, padx=5)


class MainWindow:

    logo = "img\\logo.jpeg"
    current_dir = pathlib.Path(__file__).parent.resolve()  # current directory
    logo_path = os.path.join(current_dir, logo)

    def __init__(self, root: tk.Tk, title: str, width: int, height: int) -> None:
        self.root = root
        self.root.title(title)
        self.root.geometry(str(width)+'x'+str(height))
        self.root.resizable(False, False)

        self.fontBig = tkFont.Font(family='Sketchy', size=18, weight='bold')
        self.fontNormal = tkFont.Font(family='Sketchy', size=12)

        # UPPER FRAME
        self.upFrame = tk.Frame(self.root,
                                width=int(self.root.winfo_width()),
                                background='Red')
        self.upFrame.pack(fill=tk.BOTH, anchor='n', side=tk.TOP, expand=True)

        # RIGHT TOP FRAME
        self.upLFrame = tk.Frame(self.upFrame,  background='Yellow')
        self.upLFrame.pack(fill=tk.BOTH, anchor='n', side=tk.LEFT, expand=True)
        #self.upLFrame.grid(row=0, column=0, sticky='nswe')

        self.upLFrame.grid_columnconfigure(0, weight=1)
        self.upLFrame.grid_rowconfigure(0, weight=3)
        self.upLFrame.grid_rowconfigure(1, weight=1)

        # Create an object of tkinter ImageTk

        canvas = tk.Canvas(self.upLFrame, width=210, bd=2)
        canvas.grid(row=0, column=0, sticky='nswe', padx=5, pady=5)
        logo = Image.open(self.logo_path)
        logo_resized = logo.resize((300, 280), Image.Resampling.LANCZOS)

        logo = ImageTk.PhotoImage(logo_resized)
        canvas.create_image(145, 140, image=logo)

        self.runBirgoSender = new_button("Start BirgoSender",
                                         self.upLFrame, self.fontBig,
                                         self.upLFrame.winfo_width(), 1, 1, 'nswe')

        # CENTER TOP FRAME
        self.upCFrame = tk.Frame(
            self.upFrame, height=height*0.9,  width=int(self.root.winfo_width()/7*2), background='Black')
        self.upCFrame.pack(fill=tk.BOTH, anchor='n',
                           side=tk.LEFT, expand=True)
        # self.upCFrame.grid(row=0, column=1, sticky='nsew')

        self.loadNumbers = new_button("Load Phone Number",
                                      self.upCFrame, self.fontNormal,
                                      10, 0, 1, 'nswe', False, True)

        self.loadMessage = new_button("Load Message",
                                      self.upCFrame, self.fontNormal,
                                      10, 1, 1, 'nswe', False, True)

        self.loadImage = new_button("Load Image",
                                    self.upCFrame, self.fontNormal,
                                    10, 2, 1, 'nswe', False, True)

        self.testNumber = new_button("Test single number",
                                     self.upCFrame, self.fontNormal,
                                     10, 3, 1, 'nswe', False, True)

        # LEFT TOP FRAME
        self.upRFrame = tk.Frame(
            self.upFrame, height=height*0.9, width=int(self.root.winfo_width()/7*2), background='Green')
        self.upRFrame.pack(fill=tk.BOTH, anchor='n',
                           side=tk.RIGHT, expand=True)
        #self.upRFrame.grid(row=0, column=2, sticky='nsew')

        self.sessionPath = new_button("Select Session Path",
                                      self.upRFrame, self.fontNormal,
                                      10, 0, 1, 'nswe', False, True)

        # PROGRESS BAR FRAME
        self.pBarFrame = tk.Frame(self.root,
                                  height=30, background='Blue')
        self.pBarFrame.pack(fill=tk.X, anchor='s', side=tk.BOTTOM, expand=True)

        self.root.mainloop()


if __name__ == '__main__':

    win = tk.Tk()
    MainWindow(win, 'BirgoSender', 700, 400)

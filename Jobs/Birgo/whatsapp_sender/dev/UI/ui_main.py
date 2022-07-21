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
from tkinter import filedialog
from tkinter.ttk import Progressbar, Separator, Spinbox


# pip installed python packages
from PIL import ImageTk, Image
from numpy import pad

# project modules
#from ..browser_setup import Whatsapp
#from pandas_util import read_number


def new_button(text: str, parent: tk.Frame, font: tkFont.Font,
               width: int = 100, pack: bool = False, func=None):
    button = tk.Button(parent, text=text, width=width,
                       bg='#186b37', fg='#ededc1', command=func)
    button['font'] = font
    if pack:
        button.pack(fill=tk.BOTH, pady=10, padx=5)


def load_numbers():
    print('called')
    file = filedialog.askopenfile(mode='r',
                                  filetypes=[('Excel File', '*.xlsx')])
    if file:
        filepath = os.path.abspath(file.name)
        phone_book = read_number(filepath)
        print(phone_book)


class MainWindow:

    logo = "img\\logo.jpeg"
    current_dir = pathlib.Path(__file__).parent.resolve()  # current directory
    logo_path = os.path.join(current_dir, logo)
    bgcolor = '#faf0c7'

    def __init__(self, root: tk.Tk, title: str, width: int, height: int) -> None:
        self.root = root
        self.root.title(title)
        self.root.geometry(str(width)+'x'+str(height))
        self.root.resizable(False, False)

        self.fontBig = tkFont.Font(family='Sketchy', size=18, weight='bold')
        self.fontNormal = tkFont.Font(family='Sketchy', size=12, weight='bold')
        self.fontSmall = tkFont.Font(family='Arial', size=10)

        # UPPER FRAME
        self.upFrame = tk.Frame(self.root,
                                width=int(self.root.winfo_width()),
                                background=self.bgcolor)
        self.upFrame.pack(fill=tk.BOTH, anchor='n', side=tk.TOP, expand=True)

        # LEFT TOP FRAME
        self.upLFrame = tk.Frame(self.upFrame, background=self.bgcolor)
        self.upLFrame.pack(fill=tk.BOTH, anchor='n', side=tk.LEFT, expand=True)

        self.upLFrame.grid_columnconfigure(0, weight=1)
        self.upLFrame.grid_rowconfigure(0, weight=3)
        self.upLFrame.grid_rowconfigure(1, weight=1)

        # Create an object of tkinter ImageTk

        canvas = tk.Canvas(self.upLFrame, width=250, bd=2)
        canvas.grid(row=0, column=0, sticky='nswe', padx=5, pady=5)
        logo = Image.open(self.logo_path)
        logo_resized = logo.resize((300, 280), Image.Resampling.LANCZOS)

        logo = ImageTk.PhotoImage(logo_resized)
        canvas.create_image(145, 140, image=logo)

        self.runBirgoSender = tk.Button(self.upLFrame,
                                        text="Start BirgoSender",
                                        width=self.upLFrame.winfo_width(),
                                        bg='#186b37', fg='#ededc1',
                                        command=None)
        self.runBirgoSender.grid(row=1,
                                 column=0,
                                 padx=5, pady=5,
                                 sticky='nswe')
        self.runBirgoSender['font'] = self.fontBig

        Separator(master=self.upFrame,
                  orient=tk.VERTICAL,
                  style='TSeparator',
                  ).pack(fill=tk.Y, side=tk.LEFT, pady=8, padx=0)

        # CENTER TOP FRAME
        self.upCFrame = tk.Frame(self.upFrame, background=self.bgcolor)
        self.upCFrame.pack(fill=tk.BOTH, anchor='n',
                           side=tk.LEFT, expand=True)

        self.loadNumbers = new_button("Load Phone Book",
                                      self.upCFrame, self.fontNormal,
                                      15, True, func=load_numbers)

        self.loadImage = new_button("Load Image",
                                    self.upCFrame, self.fontNormal,
                                    10, True)

        self.inputMsgLbl = tk.Label(self.upCFrame, text='Enter Your Message',
                                    bg=self.bgcolor, fg='#186b37')
        self.inputMsgLbl['font'] = self.fontNormal
        self.inputMsgLbl.pack(pady=5, padx=5)

        self.inputMsg = tk.Text(self.upCFrame, height=7, width=1)
        self.inputMsg.pack(fill=tk.X, pady=5, padx=5)
        self.inputMsg.insert('end', 'Enter your message here')
        self.inputMsg['font'] = self.fontSmall

        self.testNumber = new_button("Test single number",
                                     self.upCFrame, self.fontNormal,
                                     10, True)

        self.testNumberInput = tk.Text(self.upCFrame, height=1.1, width=1)
        self.testNumberInput.pack(fill=tk.X, pady=5, padx=5)
        self.testNumberInput.insert('end', 'Enter a phone number')
        self.testNumberInput['font'] = self.fontSmall

        Separator(master=self.upFrame,
                  orient=tk.VERTICAL,
                  style='TSeparator',
                  ).pack(fill=tk.Y, side=tk.LEFT, pady=8, padx=0)

        # RIGHT TOP FRAME
        self.upRFrame = tk.Frame(self.upFrame, background=self.bgcolor)
        self.upRFrame.pack(fill=tk.BOTH, anchor='n',
                           side=tk.RIGHT, expand=True)

        self.advOptions = tk.Label(self.upRFrame, text='Advanced Options',
                                   bg=self.bgcolor, fg='#186b37')
        self.advOptions['font'] = self.fontNormal
        self.advOptions.pack(pady=10, padx=5)

        self.sessionPath = new_button("Set Session Path",
                                      self.upRFrame, self.fontNormal,
                                      8, True)

        # RIGHT TOP 2ND FRAME
        self.upR2ndFrame = tk.Frame(self.upRFrame, background=self.bgcolor)
        self.upR2ndFrame.pack(fill=tk.BOTH, anchor='n',
                              side=tk.RIGHT, expand=True)

        self.loadTime = tk.Label(self.upR2ndFrame, text='Loading Time:',
                                 bg=self.bgcolor, fg='#186b37')
        self.loadTime['font'] = self.fontNormal
        self.loadTime.grid(row=0, column=0, pady=10, padx=5)

        self.spinLoadTime = Spinbox(self.upR2ndFrame, from_=1, to=10, width=6)
        self.spinLoadTime.grid(row=0, column=1, sticky='w')
        self.spinLoadTime.set('3')

        self.sendTime = tk.Label(self.upR2ndFrame, text='Sending Time:',
                                 bg=self.bgcolor, fg='#186b37')
        self.sendTime['font'] = self.fontNormal
        self.sendTime.grid(row=1, column=0)

        self.spinSendTime = Spinbox(self.upR2ndFrame, from_=1, to=10, width=6)
        self.spinSendTime.grid(row=1, column=1, sticky='w')
        self.spinSendTime.set('2.5')

        # PROGRESS BAR FRAME
        self.pBarFrame = tk.Frame(self.root,
                                  height=30, background='#186b37')
        self.pBarFrame.pack(fill=tk.X, anchor='s', expand=True)

        self.pBar = Progressbar(
            self.pBarFrame, length=100, mode='indeterminate')
        self.pBar['value'] = 50
        self.pBar.pack(fill=tk.X, expand=True)

        self.stringvar = tk.StringVar()
        self.stringvar.set('0/100 msg sent')
        self.PBarTxt = tk.Label(
            self.pBarFrame, text=self.stringvar.get(), font=('Arial', 10), bg='#186b37', fg='#faf0c7')
        self.PBarTxt.pack(fill=tk.X, side=tk.TOP, expand=True)
        self.root.mainloop()

    # FUNCTIONS


if __name__ == '__main__':

    win = tk.Tk()
    MainWindow(win, 'BirgoSender', 800, 400)

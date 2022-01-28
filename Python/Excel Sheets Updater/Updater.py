import pandas as pd
import os
import openpyxl

# give source and local Excel files' paths
source_excel_dir = r'C:\Users\sina1\PycharmProjects\pythonProject1'
local_excel_dir = r'C:\Users\sina1\PycharmProjects\pythonProject1'

# declares source and local sheet_name
print("Enter the full sheet name of the source Excel file which data needs to retrieved from: ")
source_sheet = str(input())

print("Enter the full sheet name of the local Excel file which data needs to be written to: ")
local_sheet = str(input())

# declares source and local Excel files
source = pd.read_excel(os.path.join(source_excel_dir, 'source.xlsx'), sheet_name = source_sheet)

local = pd.read_excel(os.path.join(local_excel_dir, 'local.xlsx'), sheet_name = local_sheet)

# prints source columns
print('Source Columns List : ')
print(source.columns.values)
print()

source_column = input("Enter the source column headers which need to be copied, separated by comma(,): ")

# prints local columns
print('\nLocal Columns List : ')
print(local.columns.values)
print()
print(
    'Do you want to keep the same column name in the local file? Choose from the two options below and type in the '
    'number of your choice and press enter :\n1.Keep same the column header(s) for them columns from the '
    'source\n2.Replace column header(s)')
choice = int(input())

if choice == 1:
    local_column = source_column
else:
    local_column = input("\nEnter the local column names which need to be copied, separated by comma(,): ")

source_column_list = source_column.split(",")
local_column_list = local_column.split(",")

# copies data from source
local[local_column_list] = source[source_column_list]

# saves to local
path = os.path.join(local_excel_dir , 'local.xlsx')

# deletes copied sheet from local excel to avoid duplication
workbook = openpyxl.load_workbook(path) #opens local Excel-file
page_to_delete = workbook[local_sheet]
workbook.remove(page_to_delete) #deletes copied page(s)
workbook.save(path)
workbook.close()#saves file with changes (deleted page)

# writes data to local sheet
with pd.ExcelWriter(path , mode = 'a',engine = 'openpyxl') as writer:
    local.to_excel(writer, sheet_name = local_sheet , index = False)

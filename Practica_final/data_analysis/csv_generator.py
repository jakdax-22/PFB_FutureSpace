import pandas as pd
from sqlalchemy import create_engine
import pymysql

db_url = 'mysql+pymysql://root@localhost/practica';
engine = create_engine(db_url);
connection = pymysql.connect(
    host='localhost',
    user='root',
    database='practica'
);

#Leer datos
employee_df = pd.read_sql_table('em_empleados',engine)
project_df = pd.read_sql_table('pr_proyectos',engine)
asignation_df = pd.read_sql_table('pr_empleados_proyecto',engine)

#Exportar a CSV
employee_df.to_csv('./csv/employees.csv',index=False);
project_df.to_csv('./csv/projects.csv',index=False);
asignation_df.to_csv('./csv/asignations.csv',index=False);


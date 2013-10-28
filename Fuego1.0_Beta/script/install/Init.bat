

set CURRENT_PATH=%cd%

set /p DB_USER=pls input database user name:
set /p DB_PASSWORD=pls input database password:

 
set DB_NAME=fuegodb

set CREATE_TALBE_PATH=%CURRENT_PATH%/CreateTable
set STATIC_DATA_PATH=%CURRENT_PATH%/StaticData


mysql -u%DB_USER% -p%DB_PASSWORD% <%CREATE_TALBE_PATH%/FeugoDLL.sql



mysql -u%DB_USER% -p%DB_PASSWORD% <%STATIC_DATA_PATH%/menu_tree.sql
mysql -u%DB_USER% -p%DB_PASSWORD% <%STATIC_DATA_PATH%/role_map_menu.sql
mysql -u%DB_USER% -p%DB_PASSWORD% <%STATIC_DATA_PATH%/system_user.sql
mysql -u%DB_USER% -p%DB_PASSWORD% <%STATIC_DATA_PATH%/trans_event.sql 
mysql -u%DB_USER% -p%DB_PASSWORD% <%STATIC_DATA_PATH%/trans_event_type.sql 

mysql -u%DB_USER% -p%DB_PASSWORD% <%STATIC_DATA_PATH%/assets_type.sql

pause

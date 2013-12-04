set CURRENT_PATH=%cd%
set BACKUP_PATH=%CURRENT_PATH%\backup

set d=%date:~0,10%
set d=%d:-=%
set d=%d: =0%
set t=%time:~0,8%
set t=%t::=%
set t=%t: =0%
 
set BACKUP_FILE=backup_file_%p%%d%%t%.sql
mysqldump -hlocalhost -uroot -p1234 feugoDB > %BACKUP_PATH%\%BACKUP_FILE%
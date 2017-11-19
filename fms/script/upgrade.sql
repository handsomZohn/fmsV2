/**
 * 清除PDF文件类型 
 */
delete  FROM [fmsv2].[dbo].[fms_filetype] where code='pdf'
go
delete  FROM [fmsv2].[dbo].[fms_filelog] where identify_code='pdf'
go
delete  FROM [fmsv2].[dbo].[fms_filelog_history] where identify_code='pdf'
go
delete  FROM [fmsv2].[dbo].[fms_user_monthlyreport] where file_type='pdf'
go
delete  FROM [fmsv2].[dbo].[fms_org_monthlyreport] where file_type='pdf'
go
/**
 * 处理权限表
 */
delete from [fmsv2].[dbo].[fms_permission]
go
insert into [fmsv2].[dbo].[fms_permission] select * from [fmsv2_online].[dbo].[fms_permission]
go
delete from [fmsv2].[dbo].[fms_role_permission]
go
insert  [fmsv2].[dbo].[fms_role_permission] SELECT * FROM [fmsv2_online].[dbo].[fms_role_permission]
go
delete  FROM [fmsv2test].[dbo].[fms_role]
go
insert into [fmsv2].[dbo].[fms_role] SELECT *  FROM [fmsv2_online].[dbo].[fms_role]
go

/**
 * 更改日志的密级类型和文件类型
 */
update  [fmsv2test].[dbo].[fms_filetype]  set suffix_name= substring(suffix_name,2,LEN(suffix_name))  where suffix_name like '.%'
go
update [fmsv2test].[dbo].[fms_filetype] set code=suffix_name
go
update  [fmsv2].[dbo].[fms_filelog] set secruity_level= substring(suffix_name,4,1)   where len(secruity_level)>1
go
update  [fmsv2].[dbo].[fms_filelog]  set suffix_name= substring(suffix_name,2,LEN(suffix_name))  where suffix_name like '.%'
go
update  [fmsv2].[dbo].[fms_filelog]  set  identify_code=suffix_name
go

ALTER TABLE [fmsv2].[dbo].[fms_filelog]	ADD [processmd5] [varchar](50) 
go
ALTER TABLE [fmsv2].[dbo].[fms_filelog]	ADD [process_path] [varchar](500)
go






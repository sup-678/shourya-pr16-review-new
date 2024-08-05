-- Source system code is now obsolete, this script removes the column.

alter table order_archive drop column source_system_code;

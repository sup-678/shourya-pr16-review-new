-- Added finalized fields for external systems.

alter table orders
add column finalized boolean not null default false,
add column finalized_at timestamp;

ALTER TABLE vaccination_place
add constraint unique_organization_division unique (organization_name, division_name);
OneToOne Mapping
- If annotations like OneToOne and JoinColumn is only mentioned in the parent class then its unidirectional relationship if it is mentioned in both parent and child entity classes then its a bidirectional relationship.
- One-to-One relationship: When a record in one entity (table) is associated with exactly one record in another entity (table).
- Relationship can be built in uni-directional / bi-directional way.

Properties of @OneToOne annotation:
- FetchType - Do we want to fetch the child entities eagerly or lazily?
- CascadeType - Any operation on parent does same needs to be cascaded to child? If parent record is deleted the child record should be deleted as well - CascadeType.ALL
- targetEntity - Target entity is the child entity class.

@JoinColumn - This annotation is used to specify foreign key relationship details between 2 entities, its properties:
- name: defines the name of the foreign key column in table.
- referencedColumnName: field name inside target entity pojo class
- nullable: whether foreign key column can be nullable or not

FetchType
- Lazy: Lazily load child entity, child entity is only loaded when retrieved via getter method.
- Eager: Load all child entities along along with parent entity.
- Default FetchType is Lazy for @*ToMany and is Easy for @*ToOne.

CascadeType
- Default is NONE i.e. no change on child entity
- Cascading is a concept where we want to propagate parent entity state changes to child entity as well.
- CascadeType.Persist: Child entity table would be updated for insert in parent entity table.
- CascadeType.Merge: Child entity table would be updated for update in parent entity table.
- CascadeType.Refresh: Whenever reloading parent entity, reload child entity as well.
- CascadeType.Remove: Whenever removal in parent entity, remove child entity as well.
- CascadeType.Detach: Whenever detach parent entity from current session, detach child entity as well.
- CascadeType.All: All changes in parent entity reflect in child entity.

Note: Since there is no scenario where based upon address, I want to populate the person details hence no bi-directional relationship, only uni-directional from person to address.

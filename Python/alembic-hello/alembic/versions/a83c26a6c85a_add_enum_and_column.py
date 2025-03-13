"""Add enum and column

Enums and postgres and sqlalchemy are kind of a pain.
See: https://stackoverflow.com/questions/37848815/sqlalchemy-postgresql-enum-does-not-create-type-on-db-migrate

Revision ID: a83c26a6c85a
Revises: 99e2064da4b1
Create Date: 2022-03-16 14:09:52.277473

"""

from enum import Enum

import sqlalchemy as sa
from alembic import op
from sqlalchemy.dialects import postgresql

# revision identifiers, used by Alembic.
revision = "a83c26a6c85a"
down_revision = "99e2064da4b1"
branch_labels = None
depends_on = None


class Type(Enum):
    """An enum type for a new column."""

    GOOD = "g00d"
    BAD = "b4d"
    EVIL = "3v1l"


type_values = [v.value for v in Type]  # ["g00d, "b4d", "3v1l"]


def upgrade():
    """Add an enum type and a column."""

    pg_enum_type = postgresql.ENUM(*type_values, name="Type")
    pg_enum_type.create(op.get_bind())

    type_column = sa.Column(
        "type",
        sa.Enum(*type_values, name="Type"),
        nullable=False,
        default=Type.GOOD.value,
        server_default=Type.GOOD.value,
    )
    op.add_column("mytable", type_column)


def downgrade():
    """Remove an enum type and a column."""
    op.drop_column("mytable", "type")

    pg_enum_type = postgresql.ENUM(name="Type")
    pg_enum_type.drop(op.get_bind())

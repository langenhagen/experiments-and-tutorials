"""create table

Autogenerated by calling:

    alembic revision -m "create table"

Revision ID: 99e2064da4b1
Revises: 
Create Date: 2022-03-16 14:00:51.217047

"""
import sqlalchemy as sa
from alembic import op

# revision identifiers, used by Alembic.
revision = "99e2064da4b1"
down_revision = None
branch_labels = None
depends_on = None


def upgrade():
    """Create a table"""
    op.create_table(
        "mytable",
        sa.Column("id", sa.Integer, primary_key=True),
        sa.Column("name", sa.String(50), nullable=False),
    )


def downgrade():
    """Drop a table."""
    op.drop_table("mytable")
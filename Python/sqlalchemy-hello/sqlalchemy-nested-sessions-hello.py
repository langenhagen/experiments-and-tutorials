"""Showcase the behavior of nested SQLAlchemy sessions."""

from contextlib import contextmanager

from sqlalchemy import Column, Integer, String, create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

Base = declarative_base()
engine = create_engine("sqlite:///mydb.db", echo=False)
Session = sessionmaker(bind=engine)


@contextmanager
def session_scope():
    """Provide a transactional scope around a series of database operations."""
    session = Session()
    try:
        yield session
        session.commit()
    except:  # pylint: disable=W0703; # noqa
        session.rollback()
        raise
    finally:
        session.close()


class MyModel(Base):
    """A DB model for showcasing."""

    __tablename__ = "mytable"

    id = Column(Integer, primary_key=True)
    my_column = Column(String)

    def __str__(self) -> str:
        """Return the string representation of the object."""
        return f"MyModel(id={self.id}, my_column={self.my_column})"


def do_nested_session(a: MyModel) -> None:
    """Change an item from an outer session."""
    with session_scope() as session:
        # session.add(a)  # doesn't work, obviously
        print(f"before updating {a.my_column=}")
        a.my_column = "Yay!"  # is effective
        print(f"after updating {a.my_column=}")


def do_outer_session() -> None:
    """First DB session inception level."""
    with session_scope() as session:
        a = MyModel(my_column="foo")
        session.add(a)

        do_nested_session(a)


def show_db_contents() -> None:
    """Print the contents of mytable"""
    print(f"\nAll MyModel DB entries:")
    with session_scope() as session:
        for i, m in enumerate(session.query(MyModel), 1):
            print(f"{i}: {m}")


def main() -> None:
    """Create a session via context manager, and inside this context, create
    another session via context manager. See how this works."""
    Base.metadata.create_all(bind=engine)

    do_outer_session()

    show_db_contents()


if __name__ == "__main__":
    main()

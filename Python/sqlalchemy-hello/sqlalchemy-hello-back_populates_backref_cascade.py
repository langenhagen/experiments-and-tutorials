#!/usr/bin/env python3
"""
Experiment with the differences between `back_populates`, `backref` and cascade.
in relationships.

According to SQLAlchemy docs, better use `back_populates` as opposed to the
legacy `backref`.
"""
from bpython import embed
from sqlalchemy import Column, ForeignKey, Integer, String, create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import relationship, sessionmaker

Base = declarative_base()


class BackPopParent(Base):
    """A parent model class using back_populates."""

    __tablename__ = "back_pop_parent"

    id = Column(Integer, primary_key=True)
    name = Column(String, unique=True)
    children = relationship(
        "BackPopChild",
        back_populates="parent",
        cascade="all, delete-orphan",
        foreign_keys="BackPopChild.parent_id",
    )

    def __str__(self) -> str:
        """Helper function for pretty printing."""
        return f"id={self.id}  name={self.name}  children={self.children}"


class BackPopChild(Base):
    """A child model class using back_populates."""

    __tablename__ = "back_pop_child"

    id = Column(Integer, primary_key=True)
    name = Column(String, unique=True)
    parent_id = Column(Integer, ForeignKey("back_pop_parent.id"), nullable=False)
    parent = relationship(
        "BackPopParent",
        back_populates="children",
        cascade=False,
        foreign_keys=parent_id,
    )

    def __str__(self) -> str:
        """Helper function for pretty printing."""
        return f"id={self.id}  name={self.name}  parent={self.parent}"


class BackrefParent(Base):
    """A parent model class using backref."""

    __tablename__ = "backref_parent"

    id = Column(Integer, primary_key=True)
    name = Column(String, unique=True)
    children = relationship(
        "BackrefChild",
        backref="parent",
        cascade="all, delete-orphan",
        foreign_keys="BackrefChild.parent_id",
    )

    def __str__(self) -> str:
        """Helper function for pretty printing."""
        return f"id={self.id}  name={self.name}  children={self.children}"


class BackrefChild(Base):
    """A child model class using ref."""

    __tablename__ = "backref_child"

    id = Column(Integer, primary_key=True)
    name = Column(String, unique=True)
    parent_id = Column(Integer, ForeignKey("backref_parent.id"), nullable=False)

    def __str__(self) -> str:
        """Helper function for pretty printing."""
        return f"id={self.id}  name={self.name}  parent={self.parent}"


def main():
    """Main program entry point."""
    engine = create_engine("sqlite:///database.db", echo=False)
    Base.metadata.create_all(engine)
    Session = sessionmaker(bind=engine)
    session = Session()

    print("*** back_populates ***")

    a = BackPopParent(name="Foo")
    b = BackPopChild(name="Bar")
    a.children.append(b)
    session.add(a)
    print(f"a.name={a.name}")
    print(f"b.name={b.name}")
    print(f"len(a.children)={len(a.children)}")
    print(f"a.children[0].name={a.children[0].name}")
    print(a.name)

    print("*** backref ***")

    c = BackrefParent(name="Boo")
    d = BackrefChild(name="Baz")
    c.children.append(d)
    session.add(c)
    print(f"c.name={c.name}")
    print(f"d.name={d.name}")
    print(f"len(c.children)={len(c.children)}")
    print(f"c.children[0].name={c.children[0].name}")

    session.commit()

    embed(locals_=locals(), banner="\nDropping to interactive shell\n")


if __name__ == "__main__":
    main()

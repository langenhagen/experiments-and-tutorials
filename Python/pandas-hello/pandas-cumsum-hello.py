#!/usr/bin/env python
"""Showcase the usage of pandas.Series.cumsum().

Also showcase that logging a dataframe prints it nicely."""
import logging

import pandas as pd

logging.basicConfig(
    format="[%(levelname)s]: %(filename)s:%(lineno)d: %(message)s",
    level=logging.INFO,
)

logger = logging.getLogger(__name__)

df = pd.DataFrame({"a": [1, 2, 1, 3]})
logger.info("Dataframe:\n%s", df)

c = df["a"].cumsum()
logger.info("Dataframe with cumsum on column 'a':\n%s", c)

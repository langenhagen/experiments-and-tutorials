#!/usr/bin/env python
"""Showcase usage of the 3rd party package `yfinance`."""
import yfinance as yf

# define the ticker symbol
tickerSymbol = "SWDA.SW"

# get data on this ticker
tickerData = yf.Ticker(tickerSymbol)

# get the historical prices for this ticker
tickerDf = tickerData.history(period="1d", start="2010-1-1", end="2052-1-25")

# see your data
print(tickerDf)

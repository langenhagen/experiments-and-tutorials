#!/usr/bin/env python
"""Showcase usage of the 3rd party package `yfinance`."""
import datetime as dt

import forex_python.converter
import yfinance as yf

# define the ticker symbol
ticker_symbol = "SWDA.SW"

# get data on this ticker
ticker_data = yf.Ticker(ticker_symbol)

# get the historical prices for this ticker
df = ticker_data.history(period="1d", start="2010-1-1", end="2052-1-25")

# see your data
rate_converter = forex_python.converter.CurrencyRates()
print(df * rate_converter.get_rate("USD", "EUR", dt.datetime(2021, 2, 12)))

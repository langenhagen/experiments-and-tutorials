function blah()
    print( a() )
end

local function a()
    return 5
end

blah() -- a() will cause "attempt to call a nil value"

"""
List connected devices.

based on:
/opt/ids-peak_2.1.0.0-14251_amd64/share/doc/ids-peak/ids_peak-1.4.2/html/index.html#Python

Just like the other Python code, doesn't work on my setup. the C++ stuff works.
"""
from ids_peak import ids_peak

try:
    ids_peak.Library.Initialize()
    device_manager = ids_peak.DeviceManager.Instance()
    device_manager.Update()
    device_descriptors = device_manager.Devices()
    for device_descriptor in device_descriptors:
        print(device_descriptor.DisplayName())
except ids_peak.Exception as ex:
    print("Exception: " + str(ex))
finally:
    ids_peak.Library.Close()

print("That's it. Adios.")

from sdl_module import carlo_sdl_module_python as sdl

loc = sdl.Location()
locs = sdl.LocationList()
locstore = sdl.LocationStore()


pm = sdl.ProfileManager()

pbs = sdl.ProfileBoundStores()

# --------------------------------------------------------------------------------------------------


class TestProfileManagerListener(sdl.ProfileManagerListener):
    """ Incomplete but sufficient implementation of a ProfileManagerListener
    Please set the event listeners if you are about to use them.
    """

    profile_created_event = None
    profile_activated_event = None
    profile_deleted_event = None
    last_error_code = None

    # ----------------------------------------------------------------------------------------------

    def __init__(self):
        sdl.ProfileManagerListener.__init__(self)

    # ----------------------------------------------------------------------------------------------

    def on_create_profile_done(self, request_id, carlo_error_code):
        self.last_error_code = carlo_error_code
        self.profile_created_event.set()

    # ----------------------------------------------------------------------------------------------

    def on_activate_profile_done(self, request_id, carlo_error_code):
        self.last_error_code = carlo_error_code
        self.profile_activated_event.set()

    # ----------------------------------------------------------------------------------------------

    def on_delete_profile_done(self, request_id, carlo_error_code):
        self.last_error_code = carlo_error_code
        self.profile_deleted_event.set()


# --------------------------------------------------------------------------------------------------

class TestProfileBoundStoresInitializerListener(sdl.ProfileBoundStoresInitializerListener):
    """ Please set the profile_bound_stores_ready_event member in order to receive callbacks.
    """

    profile_bound_stores_instance = None
    profile_bound_stores_ready_event = None
    last_error_code = None

    # ----------------------------------------------------------------------------------------------

    def __init__(self, profile_bound_stores_ready_event):
        sdl.ProfileBoundStoresInitializerListener.__init__(self)
        self.profile_bound_stores_ready_event = profile_bound_stores_ready_event

    # ----------------------------------------------------------------------------------------------

    def on_create_profile_bound_stores_done(self, request_id, instance, carlo_error_code):
        self.profile_bound_stores_instance = instance
        self.last_error_code = carlo_error_code
        self.profile_bound_stores_ready_event.set()


# --------------------------------------------------------------------------------------------------

import os
from subprocess import CalledProcessError, check_call

import psutil


class dafoo:

    def kill_process(name):
        """ kills the process with the given name
        """
        for process in psutil.process_iter():
            if process.name() == name:
                log('Process "' + name + '" found. Terminating it.')
                process.kill()
                return

        log('Did not find a process named "' + name + '".')



    def delete_files(files):
        """ deletes the files with the given names
        """
        for file in files:
            os.remove(file)
            # check deletion worked?
            # If we have a handle on it in another process, who knows...
            if os.path.exists(file): log('' + file + ' could not be deleted!')



    def revoke_write_permissions(files):
        """ removes the write permissions for the files
            with the given names
        """
        for file in files:
            # TODO revoke read access
            # TODO check read access has been revoked
            # os.chmod ?
            pass
        pass


    def grant_write_permissions(files):
        """ grants write permissions for the files
            with the given names
        """
        # TODO maybe not necessary

        for file in files:
            # TODO grant read/write access
            # TODO check read/write access has been granted
            # os.chmod ?
            pass
        pass


    def log(msg):
        """ simple logging mechanism
        """
        print msg

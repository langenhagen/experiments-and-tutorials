def call_process( name, args):
    '''Call subprocess.check_call(name, args) and then checks if it was killed
       by the operating system - which is what we want :)
    '''
    try:
        subprocess.check_call([name, args])
    except subprocess.CalledProcessError as e:
        if (e.returncode != -9):
            log('ERROR: The given process "' + name + '" with args: "' + args + '" was not killed by the operating system!')
            raise
    except Exception as err:
        log( 'Unhandled exception:\n' + str(err) )


def kill_process(name):
    '''Kill the process with the given name.'''
    for process in psutil.process_iter():
        if process.name() == name:
            log('Process "' + name + '" found. Terminating it.')
            process.kill()
            return

    log('Did not find a process named "' + name + '".')

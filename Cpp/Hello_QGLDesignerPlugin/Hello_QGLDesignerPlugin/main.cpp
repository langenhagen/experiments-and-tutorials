#include "hello_qgldesignerplugin.h"
#include <QtGui/QApplication>


#include <qapplication.h>

#include "ui_hello_qgldesignerplugin.h"

class ViewerInterface : public QDialog, public Ui::Hello_QGLDesignerPluginClass
  {
  public:
    ViewerInterface() { setupUi(this); }
  };


int main(int argc, char** argv)
{
  QApplication application(argc,argv);

  ViewerInterface vi;

  vi.setWindowTitle("interface");

  vi.show();

  return application.exec();
}
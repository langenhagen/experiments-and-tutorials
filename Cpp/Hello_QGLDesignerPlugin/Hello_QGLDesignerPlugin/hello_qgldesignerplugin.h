#ifndef HELLO_QGLDESIGNERPLUGIN_H
#define HELLO_QGLDESIGNERPLUGIN_H

#include <QtGui/QMainWindow>
#include "ui_hello_qgldesignerplugin.h"

class Hello_QGLDesignerPlugin : public QDialog
{
	Q_OBJECT

public:
	Hello_QGLDesignerPlugin(QWidget *parent = 0, Qt::WFlags flags = 0);
	~Hello_QGLDesignerPlugin();

private:
	Ui::Hello_QGLDesignerPluginClass ui;
};

#endif // HELLO_QGLDESIGNERPLUGIN_H

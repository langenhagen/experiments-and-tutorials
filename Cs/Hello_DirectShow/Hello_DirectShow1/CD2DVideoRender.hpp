#include <string>

#include <DShow.h>
#include "atlbase.h" // CComPtr

#define FILTER_NAME L"Direct2D Video Renderer"


// WHUTEVR
// {269BA141-1FDE-494B-9024-453A17838B9F}
static const GUID CLSID_Direct2DVideoRenderer = 
{ 0x269ba141, 0x1fde, 0x494b, { 0x90, 0x24, 0x45, 0x3a, 0x17, 0x83, 0x8b, 0x9f } };



void main()
{
    const std::string filter_name("Direct2D Video Renderer");


    HRESULT hr = CoInitialize(NULL);


    CComPtr<IGraphBuilder> m_graph;
    CComPtr<IFilterGraph2> m_filterGraph2;
    CComPtr<IMediaControl> m_mediaCtrl;
    CComPtr<IBaseFilter>   m_renderFilter;
    CComPtr<IQualProp> m_quality;

    hr = m_graph.CoCreateInstance(CLSID_FilterGraph);
    if(FAILED(hr))
    {
        // TODO ON ERROR
    }

    hr = m_graph->QueryInterface(IID_IFilterGraph2, (void**)&m_filterGraph2);
    if(FAILED(hr))
    {
        // TODO ON ERROR
    }

    hr = m_graph->QueryInterface(IID_IMediaControl, (void**)&m_mediaCtrl);
    if(FAILED(hr))
    {
        // TODO ON ERROR
    }

    hr = m_renderFilter.CoCreateInstance(CLSID_Direct2DVideoRenderer);
    if(FAILED(hr))
    {
        // TODO ON ERROR
    }


    hr = m_renderFilter->QueryInterface(IID_IQualProp, (void**)&m_quality);
    if(FAILED(hr))
    {
        // TODO ON ERROR
    }


    hr = m_filterGraph2->AddFilter(m_renderFilter, FILTER_NAME);
    if(FAILED(hr))
    {
        // TODO ON ERROR
    }

}
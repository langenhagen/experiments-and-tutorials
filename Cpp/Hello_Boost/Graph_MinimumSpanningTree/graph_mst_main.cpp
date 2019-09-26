#define HELLO

#include <barn_common.hpp>

#include <boost/graph/adjacency_list.hpp>
#include <boost/graph/prim_minimum_spanning_tree.hpp>


using namespace boost;
using namespace std;

void main() {
    typedef float weight_t;
    typedef adjacency_list<vecS, vecS, undirectedS, property<vertex_distance_t, int>, property <edge_weight_t, weight_t>> Graph;
    typedef std::pair<int,int> Edge;
    typedef Graph::edge_iterator EdgeIterator;
    typedef std::pair<EdgeIterator, EdgeIterator> EdgeIteratorPair;
    typedef property_map<Graph, edge_weight_t>::type WeightMap;
    


    /*
    //SMALL GRAPH
    const int n_nodes = 5;
    vector<Edge> edges;
    vector<weight_t> weights;

    // intermediate arrays just for convenient initialisation of the graph
    Edge     edge_arr[]   = { Edge(0, 2), Edge(1, 3), Edge(1, 4), Edge(2, 1), Edge(2, 3), Edge(3, 4), Edge(4, 0) };
    weight_t weight_arr[] = {          1,          1,          2,          7,          3,          1,          1 };

    edges   = vector<Edge>( edge_arr, edge_arr + sizeof(edge_arr) / sizeof(edge_arr[0]));
    weights = vector<weight_t>( weight_arr, weight_arr + sizeof(weight_arr) / sizeof(weight_arr[0]));
    /**/


    
    // ARBITRARY FULLY CONNECTED GRAPH
    int n_nodes;
    cout << "Enter # nodes: "; cin >> n_nodes;
    vector<Edge> edges;
    vector<weight_t> weights;

    for( int i=0; i<n_nodes; ++i)
    for( int j=i+1; j<n_nodes; ++j) {
        edges.push_back( Edge(i,j));
        weights.push_back( weight_t( rand() % 100000 / 1000.f));
    }
    /**/


    $ " *** GRAPH CREATION COMPLETE ***\n";
    

    Graph g(edges.begin(), edges.end(), weights.begin(), n_nodes);
    WeightMap weight_map = get(edge_weight_t(), g);
    std::vector<graph_traits<Graph>::vertex_descriptor> mst(num_vertices(g));
    
    prim_minimum_spanning_tree(g, &mst[0]);


    $ "Minimum spanning tree mapping:";
    for (std::size_t i = 0; i != mst.size(); ++i)
        if (mst[i] != i) {
            $ "parent[" << i << "] = " << mst[i]; 
        } else
            $ "parent[" << i << "] = no parent";


    $ "\nMinimum spanning tree:";
    EdgeIteratorPair edges_pair = boost::edges(g);
    for( EdgeIterator e = edges_pair.first; e != edges_pair.second; ++e) {

        if( mst[e->m_source] == e->m_target || mst[e->m_target] == e->m_source)
            $ *e << "  :  " << get(weight_map, *e);
    }


    
    getch();
}


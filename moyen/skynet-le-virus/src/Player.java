import java.util.*;
import java.util.stream.Stream;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // the total number of nodes in the level, including the gateways
        int L = in.nextInt(); // the number of links
        int E = in.nextInt(); // the number of exit gateways
        List<Link> links = new ArrayList<Link>(N);
        for (int i = 0; i < L; i++) {
            int N1 = in.nextInt(); // N1 and N2 defines a link between these nodes
            int N2 = in.nextInt();
            links.add(new Link(N1, N2));
        }
        List<Integer> gateways = new ArrayList<>(E);
        for (int i = 0; i < E; i++) {
            int EI = in.nextInt(); // the index of a gateway node
            gateways.add(EI);
        }

        Network network = new Network(links, gateways);

        // game loop
        while (true) {
            int SI = in.nextInt(); // The index of the node on which the Skynet agent is positioned this turn

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            network.setAgent(SI);
            Link linkToSever = network.getLinkToSever();
            System.out.println(linkToSever); // Example: 0 1 are the indices of the nodes you wish to sever the link between
            network.linkSevered(linkToSever);
        }
    }
}

class Network{
    private List<Link> links;
    private List<Integer> gateways;
    private int agent;

    public Network(List<Link> links, List<Integer> gateways) {
        this.links = links;
        this.gateways = gateways;
    }

    public void setAgent(int agent) {
        this.agent = agent;
    }

    public Link getLinkToSever() {
        return linkBetweenAgentAndGateway().orElse(links.get(0));
    }

    private Optional<Link> linkBetweenAgentAndGateway(){
        //wait java 9 to do .flatMap(Optional::stream)
        return gateways.stream()
                .map(gateway -> links.stream()
                        .filter(link -> link.equals(new Link(agent, gateway)))
                        .findAny())
                .flatMap(o -> o.map(Stream::of).orElseGet(Stream::empty))
                .findAny();
    }

    public void linkSevered(Link linkSevered) {
        links.remove(linkSevered);
    }
}

class Link {

    private int n1;
    private int n2;

    public Link(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    @Override
    public String toString() {
        return n1+" "+ n2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return (n1 == link.n1 && n2 == link.n2) || (n1 == link.n2 && n2 == link.n1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n1, n2);
    }
}
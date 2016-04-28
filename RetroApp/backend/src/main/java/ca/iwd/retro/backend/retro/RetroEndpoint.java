package ca.iwd.retro.backend.retro;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.googlecode.objectify.ObjectifyService;

import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Api(
        name = "retroApi",
        version = "v1",
        resource = "retro",
        namespace = @ApiNamespace(
                ownerDomain = "ca.iwd.retro",
                ownerName = "ca.iwd.retro",
                packagePath = ""
        )
)
public class RetroEndpoint {

    static {
        ObjectifyService.register(Retro.class);
    }

    @ApiMethod(name = "createRetro", httpMethod = ApiMethod.HttpMethod.POST)
    public Retro createRetro(Retro retro) {
        ofy().save().entity(retro).now();
        return ofy().load().entity(retro).now();
    }

    @ApiMethod(name = "getRetro", path = "retro/{projectName}", httpMethod = ApiMethod.HttpMethod.GET)
    public Retro getRetro(@Named("projectName") String projectName) {
        return ofy().load().type(Retro.class).id(projectName).now();
    }

    @ApiMethod(name = "listRetros", httpMethod = ApiMethod.HttpMethod.GET)
    public List<Retro> listRetros() {
        return ofy().load().type(Retro.class).list();
    }

    @ApiMethod(name = "addSticky", path = "retro/{projectName}/sticky",httpMethod = ApiMethod.HttpMethod.POST)
    public Retro addSticky(@Named("projectName") String projectName, @Named("message") String message) {

        // Get Retro
        Retro retro = getRetro(projectName);

        // Add sticky and save
        retro.getStickies().add(message);
        ofy().save().entity(retro).now();

        // Load and return fresh entity
        return ofy().load().entity(retro).now();
    }

}

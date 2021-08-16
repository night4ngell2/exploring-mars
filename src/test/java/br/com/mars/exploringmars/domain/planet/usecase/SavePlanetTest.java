package br.com.mars.exploringmars.domain.planet.usecase;

import br.com.mars.exploringmars.domain.planet.gateway.outbound.FindPlanetByNameOutbound;
import br.com.mars.exploringmars.domain.planet.gateway.outbound.SavePlanetOutbound;
import br.com.mars.exploringmars.domain.planet.model.Planet;
import br.com.mars.exploringmars.domain.planet.utils.MockUtil;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SavePlanetTest {

    @Mock
    private SavePlanetOutbound savePlanetOutbound;

    @Mock
    private FindPlanetByNameOutbound findPlanetByNameOutbound;

    @Test
    public void saveSuccess(){
        var name = "test";
        var expectedId = 1L;
        var planet = MockUtil.createPlanet(expectedId, name);
        var expectedResult = new Planet(1L, planet.getName());
        when(findPlanetByNameOutbound.execute(name)).thenReturn(null);
        when(savePlanetOutbound.execute(planet)).thenReturn(expectedResult);
        var target = new SavePlanet(savePlanetOutbound, findPlanetByNameOutbound);
        var result = target.execute(planet);
        assertNotNull(result);
        assertEquals(expectedId, expectedResult.getId());
    }

}
package com.taxidriverhk.hkadbus2.provider.impl;

import static com.taxidriverhk.hkadbus2.util.MockDataHelper.LANGUAGE_EN;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.SEARCH_RECORD_ENTITY_1;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.SEARCH_RECORD_ENTITY_2;
import static com.taxidriverhk.hkadbus2.util.MockDataHelper.SEARCH_RECORD_ENTITY_3;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.compress.utils.Sets;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.taxidriverhk.hkadbus2.model.domain.EntityOptionType;
import com.taxidriverhk.hkadbus2.repository.impl.SqlRepositoryTestBase;

public class EntityOptionsProviderImplTest extends SqlRepositoryTestBase {

    private EntityOptionsProviderImpl provider;
    
    @BeforeEach
    public void setup() {
        provider = new EntityOptionsProviderImpl(sessionFactory);
    }

    @ParameterizedTest
    @MethodSource("entityOptionsTestCases")
    public void GIVEN_entityType_WHEN_getOptions_THEN_shouldReturnUniqueOptions(
            final EntityOptionType entityType,
            final Set<String> expectedKeys,
            final Set<String> expectedValues
    ) {
        final Map<String, String> options = provider.getEntityOptions(entityType, LANGUAGE_EN);

        assertThat(options.keySet(), containsInAnyOrder(expectedKeys.toArray()));
        assertThat(options.values(), containsInAnyOrder(expectedValues.toArray()));
    }

    public static Stream<Arguments> entityOptionsTestCases() {
        return Stream.of(
                arguments(
                        EntityOptionType.ADVERTISEMENT,
                        Sets.newHashSet("mcdonalds", "fanta"),
                        Sets.newHashSet("McDonald's", "Fanta")
                ),
                arguments(
                        EntityOptionType.BUS_MODEL,
                        Sets.newHashSet("volvo-olympian-12m", "dennis-dragon-12m"),
                        Sets.newHashSet("Volvo Olympian 12M", "Dennis Dragon 12M")
                ),
                arguments(
                        EntityOptionType.CATEGORY,
                        Sets.newHashSet("restaurant", "drink"),
                        Sets.newHashSet("Restaurant", "Drink")
                ),
                arguments(
                        EntityOptionType.LICENSE_PLATE_NUMBER,
                        Sets.newHashSet("GX4965", "GX4966", "GM1204"),
                        Sets.newHashSet("GX4965", "GX4966", "GM1204")
                ),
                arguments(
                        EntityOptionType.ROUTE,
                        Sets.newHashSet("kmb-215x", "ctb-8x"),
                        Sets.newHashSet("215X", "8X")
                )
        );
    }

    @Override
    protected void setupDataForTest(final Session session) {
        final Transaction transaction = session.beginTransaction();
        session.save(SEARCH_RECORD_ENTITY_1);
        session.save(SEARCH_RECORD_ENTITY_2);
        session.save(SEARCH_RECORD_ENTITY_3);
        transaction.commit();
    }
}

package cz.johnyapps.eddiehostopky.migration.data

import cz.johnyapps.eddiehostopky.migration.domain.MigrationRepository

/**
 * Room DB doesn't work on iOS simulators.
 */
internal class InMemoryMigrationRepository : MigrationRepository {
    override suspend fun getMigratedToVersion(): Int {
        return Int.MAX_VALUE
    }

    override suspend fun setMigratedToVersion(version: Int) {
        // Ignore
    }
}
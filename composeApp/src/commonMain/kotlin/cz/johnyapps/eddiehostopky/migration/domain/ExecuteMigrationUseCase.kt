package cz.johnyapps.eddiehostopky.migration.domain

import cz.johnyapps.eddiehostopky.common.util.Logger

interface ExecuteMigrationUseCase {

    suspend operator fun invoke()
}

class LiveExecuteMigrationUseCase(
    private val migrationRepository: MigrationRepository,
    private val executeMigrateFrom0To1UseCase: MigrateFrom0To1UseCase,
) : ExecuteMigrationUseCase {

    override suspend fun invoke() {
        Logger.info(TAG, "Executing migrations")

        val migrations = mapOf(
            1 to executeMigrateFrom0To1UseCase,
        )

        var migratedToVersion = migrationRepository.getMigratedToVersion()

        migrations.forEach { (targetVersion, migration) ->
            if (migratedToVersion < targetVersion) {
                Logger.info(TAG, "Migrating from $migratedToVersion to $targetVersion")

                migration.invoke()
                migratedToVersion = targetVersion
            }
        }

        migrationRepository.setMigratedToVersion(migratedToVersion)
        Logger.info(TAG, "Migrations done")
    }

    companion object {
        private const val TAG = "ExecuteMigrationUseCase"
    }
}
